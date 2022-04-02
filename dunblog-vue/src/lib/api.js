import axios from "axios";

//接口封装
export class API {


    constructor() {

    }

    //测试
    async test(){
        const resp = await axios.get("/test");
        return resp.data.data
    }

    //登录
    async login(form){
        const resp = await axios.post("/login",form)
        return resp
    }

    //博客编辑
    async blogEdit(form){
        const resp = await axios.post("/blog/edit",form)
        return resp
    }

    //获取博客列表
    async getBlogList(currentPage,pageSize){
        const resp = await axios.get("/blog/list",{params:{currentPage:currentPage,pageSize:pageSize}})
        return resp.data.data
    }

    //获取博客详细信息
    async getBlogDetailById(blogId){
        const resp = await axios.get("/blog/get",{params:{blogId:blogId}})
        return resp.data.data

    }

    //删除博客
    async deleteBlog(data){
        const resp = await axios.post("/blog/delete",data)
        return resp.data.data
    }

    //上传图片
    async uploadImg(param,config){
        const resp = await axios.post("/upload",param,config)
        return resp.data.data
    }
}