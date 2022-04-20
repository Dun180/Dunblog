import { createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import {Pages} from "@/router/pages";


const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: Pages.BlogList,
        component: () => import("@/views/front/BlogList.vue"),

    },
    {
        path: '/blog/:blogId',
        name: Pages.BlogDetail,
        component: () => import("@/views/front/BlogDetail.vue")
    },
    {
        path: '/login',
        name: Pages.Login,
        component: () => import("@/views/end/Login.vue")
    },
    {
        path: '/backstage',
        name: Pages.Backstage,
        component: () => import("@/views/end/Backstage.vue")
    },
    {
        path: '/blog/edit',
        name: Pages.BlogEdit,
        component: () => import("@/views/end/BlogEdit.vue")
    },
    {
        path: '/category/edit',
        name: Pages.CategoryEdit,
        component: () => import("@/views/end/CategoryEdit.vue")
    },
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
