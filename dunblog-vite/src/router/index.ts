import { createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import {Pages} from "@/router/pages";
import {useStore} from "@/store";
import axios from "axios";
import {checkToken} from "@/lib/api";


const ISMOBILE = function () {
    let flag = navigator.userAgent.match(
        /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
    );
    return flag;
};

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: Pages.Front,
        component: () => ISMOBILE() ? import("@/views/front/mobile/Front.vue") : import("@/views/front/pc/Front.vue"),
        children: [
            {
                path: '',
                name: Pages.BlogList,
                component: () => import("@/views/front/pc/blog/BlogList.vue"),
                // component: () => ISMOBILE() ? import("@/views/front/blog/BlogList.vue") : import("@/views/front/category/CategoryList.vue"),
            },
            {
                path: 'blog/search',
                name: Pages.BlogSearch,
                component: () => import("@/views/front/pc/blog/BlogSearch.vue")
            },
            {
                path: 'blog/:blogId',
                name: Pages.BlogDetail,
                component: () => import("@/views/front/pc/blog/BlogDetail.vue")
            },
            {
                path: 'categories',
                name: Pages.CategoryList,
                component: () => import("@/views/front/pc/category/CategoryList.vue")
            },
            {
                path: 'category/:categoryId',
                name: Pages.CategoryDetail,
                component: () => import("@/views/front/pc/category/CategoryDetail.vue"),
            },
            {
                path: 'archives',
                name: Pages.Archives,
                component: () => import("@/views/front/pc/Archives.vue")
            },
        ],
    },
    {
        path: '/admin',
        name: Pages.Admin,
        component: () => ISMOBILE() ? import("@/views/admin/mobile/Admin.vue") : import("@/views/admin/pc/Admin.vue"),
        children: [
            {
                path: '',
                name: Pages.Dashboard,
                component: () => import("@/views/admin/pc/Dashboard.vue"),
            },
            {
                path: 'blog/list',
                name: Pages.BlogList_Admin,
                component: () => import("@/views/admin/pc/blog/BlogList.vue"),
            },
            {
                path: 'blog/edit',
                name: Pages.BlogEdit_Admin,
                component: () => import("@/views/admin/pc/blog/BlogEdit.vue"),
            },
            {
                path: 'category/list',
                name: Pages.CategoryList_Admin,
                component: () => import("@/views/admin/pc/category/CategoryList.vue"),
            },
            {
                path: 'tag/list',
                name: Pages.TagList_Admin,
                component: () => import("@/views/admin/pc/tag/TagList.vue"),
            },
            {
                path: 'diary/list',
                name: Pages.DiaryList_Admin,
                component: () => import("@/views/admin/pc/diary/DiaryList.vue"),
            },
            {
                path: 'diary/edit',
                name: Pages.DiaryEdit_Admin,
                component: () => import("@/views/admin/pc/diary/DiaryEdit.vue"),
            },
            {
                path: 'comment/list',
                name: Pages.CommentList_Admin,
                component: () => import("@/views/admin/pc/comment/CommentList.vue"),
            },
            {
                path: 'comment/moderation',
                name: Pages.CommentModeration_Admin,
                component: () => import("@/views/admin/pc/comment/CommentModeration.vue"),
            },
            {
                path: 'time/to-do-today',
                name: Pages.ToDoToday_Admin,
                component: () => import("@/views/admin/pc/time-management/ToDoToday.vue"),
            },
            {
                path: 'time/to-do-global',
                name: Pages.ToDoGlobal_Admin,
                component: () => import("@/views/admin/pc/time-management/ToDoGlobal.vue"),
            },
        ],
        meta: {
            requireAuth: true
        }
    },
    {
        path: '/login',
        name: Pages.Login_Admin,
        component: () => import("@/views/admin/pc/login/Login.vue"),
    },
    {
        path: '/error',
        name: Pages.Error,
        component: () => import("@/views/error/Error.vue"),
    }

]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

// 路由判断登录 根据路由配置文件的参数
router.beforeEach((to, from, next) => {
    const store = useStore()
    if (to.matched.some(record => record.meta.requireAuth)) { // 判断该路由是否需要登录权限
        const token = localStorage.getItem("token")
        if (token) { // 判断当前的token是否存在 ； 登录存入的token
            if (to.path === '/login') {
                store.REMOVE_INFO()
                next()
            } else {
                //校验token合法性
                //axios异步向后端请求数据验证
                checkToken(token)
                    .then(response => {
                    if(!response.data.checkResult){
                        console.log('校验失败')
                        next({path:'/error'})
                    }else{
                        next()
                    }
                }).catch( (error) => {
                    console.log(error);
                })


            }
        } else {
            next({
                path: '/'
            })
        }
    } else {
        next()
    }
})



export default router
