import { defineStore } from 'pinia'

// useStore 可以是 useUser、useCart 之类的任何东西
// 第一个参数是应用程序中 store 的唯一 id
export const useStore = defineStore('main', {
    state:() => {
        return{
            //私有属性
            token: localStorage.getItem("token"),
            userInfo: JSON.parse(sessionStorage.getItem("userInfo") || ''),
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
        SET_TOKEN: (state:any,token:any) => {
            state.token = token
            localStorage.setItem("token", token)
        },
        SET_USERINFO: (state:any,userInfo:any) => {
            state.userInfo = userInfo
            sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
        },
        REMOVE_INFO: (state:any) => {
            state.token = ''
            state.userInfo = {}
            localStorage.setItem("token", '')
            sessionStorage.setItem("userInfo", JSON.stringify(''))
        },
    }
})