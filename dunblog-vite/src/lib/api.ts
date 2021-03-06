import http from "@/utils/http";
import axios from "axios";
import {BlogProfile, BlogProfiles} from "@/models/blog";
import {Response, Result} from "@/models/result";
import {CategoryInfo} from "@/models/category";
import {TagInfo} from "@/models/tag";
import {DiaryInfo} from "@/models/diary";
import {CommentInfo} from "@/models/comment";
import {ToDoListInfo} from "@/models/toDoList";


//#region login
//登录
export async function login(form: any){
    return new Promise<Response>(async (resolve, reject) => {
        await axios.post("/login", form)
            .then((res) => {
                resolve(res);
            })
            .catch((err) => {
                reject(err);
            });
    })
}

//检查token
export async function checkToken(token: any){
    return await http.get<Result<{ checkResult:boolean }>>('/checkToken', null,{token:token})
}

//#endregion

//#region blog
//获取博客列表
export async function getBlogList(currentPage: number,pageSize: number,state?:number){
    return await http.get<Result<BlogProfiles>>('/blog/list',{currentPage:currentPage,pageSize:pageSize,state:state})
}

//获取博客详细信息
export async function getBlogDetailById(blogId: number){
    return await http.get<Result<BlogProfile>>("/blog/get",{blogId:blogId})
}

//删除博客
export async function deleteBlog(data: any){
    return await http.post<Result<Object>>("/blog/delete",data)
}

//上传图片
export async function uploadImg(param: any,config: any){
    return await http.post<Result<Object>>("/upload",param,config)
}

//博客编辑
export async function blogEdit(form: any){
    return await http.post<Result<Object>>("/blog/edit",form)
}

//点赞
export async function likeBlog(blogId: number){
    return await http.get<Result<Object>>("/blog/like",{blogId:blogId})
}

//获取日历热图数据
export async function getCalendarHeatmapData() {
    return await http.get<Result<Object>>("/blog/date")
}

//搜索博客
export async function searchBlog(searchContent: string){
    return await http.post<Result<BlogProfile[]>>("/blog/search",{searchContent:searchContent})
}
//#endregion

//#region category
//分类列表
export async function getCategoryList(){
    return await http.get<Result<CategoryInfo[]>>("/category/list")
}

//通过id获取分类信息
export async function getCategoryInfoById(id: number){
    return await http.get<Result<CategoryInfo>>("/category/get",{categoryId:id})
}

//分类编辑
export async function editCategory(data: any){
    return await http.post<Result<Object>>("/category/edit",data)
}

//删除分类
export async function deleteCategory(data: any){
    return await http.post<Result<Object>>("/category/delete",data)
}

//添加分类
export async function addCategory(data: any){
    return await http.post<Result<Object>>("/category/add",data)
}


//根据分类id获取博客列表
export async function getBlogListByCategoryId(categoryId: number, currentPage: number, pageSize: number) {
    return await http.get<Result<BlogProfiles>>('/category/'+categoryId+'/blogs', {
        currentPage: currentPage,
        pageSize: pageSize
    })

}
//#endregion

//#region tag

//标签列表
export async function getTagList(){
    return await http.get<Result<TagInfo[]>>("/tag/list")
}

//标签编辑
export async function editTag(data: any){
    return await http.post<Result<Object>>("/tag/edit",data)
}

//删除标签
export async function deleteTag(data: any){
    return await http.post<Result<Object>>("/tag/delete",data)
}

//添加标签
export async function addTag(data: any){
    return await http.post<Result<Object>>("/tag/add",data)
}

//关联博客和标签
export async function relBlogAndTag(blogId: number, tags: number[]){
    return await http.post<Result<Object>>("/tag/rel",{blogId:blogId,tags:tags})
}
//#endregion

//#region diary

//日记列表
export async function getDiaryList(){
    return await http.get<Result<DiaryInfo[]>>("/diary/list")
}

//获取日记
export async function getDiary(diaryId: number){
    return await http.get<Result<DiaryInfo>>("/diary/get", {diaryId:diaryId})
}

//日记编辑
export async function editDiary(data: any){
    return await http.post<Result<Object>>("/diary/edit",data)
}

//删除日记
export async function deleteDiary(data: any){
    return await http.post<Result<Object>>("/diary/delete",data)
}

//添加日记
export async function addDiary(data: any){
    return await http.post<Result<Object>>("/diary/add",data)
}
//#endregion

//#region comment
//根据博客id获取评论列表
export async function getCommentListByBlogId(blogId: number){
    return await http.get<Result<CommentInfo[]>>("/comment/list",{blogId:blogId})
}

//添加评论
export async function addComment(comment: CommentInfo){
    return await http.post<Result<CommentInfo>>("/comment/add",comment)
}

//删除评论
export async function deleteComment(data: any){
    return await http.post<Result<Object>>("/comment/delete",data)
}

//查询
export async function queryComment(data: Object){
    return await http.post<Result<CommentInfo[]>>("/comment/query",data)
}

//审核
export async function reviewComment(data: Object){
    return await http.post<Result<CommentInfo[]>>("/comment/review",data)
}


//#endregion

//#region toDOList

//获取今日待办
export async function getToDoToday() {
    return await http.get<Result<ToDoListInfo[]>>("/todo/today")
}

//添加待办
export async function addToDoList(todoList: ToDoListInfo){
    return await http.post<Result<Object>>("/todo/add",todoList)
}

//待办编辑
export async function editToDoList(todoList: ToDoListInfo){
    return await http.post<Result<Object>>("/todo/edit",todoList)
}

//#endregion
