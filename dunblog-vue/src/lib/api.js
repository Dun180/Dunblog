import axios from "axios";

//接口封装
export class API {
    server_url;

    constructor(server_url) {
        this.server_url = server_url;
    }

    //测试
    async test(){
        const resp = await axios.get(this.server_url+"/test");
        return resp.data.data
    }

    //登录
    async login(form){
        const resp = await axios.post(this.server_url+"/login",form)
        return resp
    }

    //博客编辑
    async blogEdit(form){
        const resp = await axios.post(this.server_url+"/blog/edit",form)
        return resp
    }

    //获取博客列表
    async getBlogList(currentPage,pageSize){
        const resp = await axios.get(this.server_url+"/blog/list",{params:{currentPage:currentPage,pageSize:pageSize}})
        return resp.data.data
    }

    //获取博客详细信息
    async getBlogDetailById(blogId){
        const resp = await axios.get(this.server_url+"/blog/get",{params:{blogId:blogId}})
        return resp.data.data

    }

    //删除博客
    async deleteBlog(data){
        const resp = await axios.post(this.server_url+"/blog/delete",data)
        return resp.data.data
    }

    //上传图片
    async uploadImg(param,config){
        const resp = await axios.post(this.server_url+"/upload",param,config)
        return resp.data.data
    }
}