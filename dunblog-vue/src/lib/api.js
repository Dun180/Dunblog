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
}