import { defineStore } from 'pinia'

// useStore 可以是 useUser、useCart 之类的任何东西
// 第一个参数是应用程序中 store 的唯一 id
export const useStore = defineStore('main', {
    state:() => {
        return {
            //私有属性
            token: localStorage.getItem("token"),
            userInfo: sessionStorage.getItem("userInfo") || '',
        }
    },
    getters:{
        //  get
        getUser: state => {
            return state.userInfo
        },
        getToken: state => {
            return state.token
        }
    },
    actions:{
        //  set
        SET_TOKEN(token:any){
            this.token = token
            localStorage.setItem("token", token)
        },
        SET_USERINFO (userInfo:any) {
            this.userInfo = userInfo
            sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
        },
        REMOVE_INFO(){
            this.token = ''
            this.userInfo = ''
            localStorage.setItem("token", '')
            sessionStorage.setItem("userInfo", JSON.stringify(''))
        },
    }
})
