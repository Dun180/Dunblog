import { createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import {Pages} from "@/router/pages";


const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: Pages.Front,
        component: () => import("@/views/front/Front.vue"),
        children: [
            {
                path: '',
                name: Pages.BlogList,
                component: () => import("@/views/front/BlogList.vue"),
            },
            {
                path: 'blog/:blogId',
                name: Pages.BlogDetail,
                component: () => import("@/views/front/BlogDetail.vue")
            },
            {
                path: 'login',
                name: Pages.Login,
                component: () => import("@/views/end/Login.vue")
            },
            {
                path: 'backstage',
                name: Pages.Backstage,
                component: () => import("@/views/end/Backstage.vue")
            },
            {
                path: 'blog/edit',
                name: Pages.BlogEdit,
                component: () => import("@/views/end/BlogEdit.vue")
            },
            {
                path: 'category/edit',
                name: Pages.CategoryEdit,
                component: () => import("@/views/end/CategoryEdit.vue")
            },
            {
                path: 'categories',
                name: Pages.CategoryList,
                component: () => import("@/views/front/CategoryList.vue")
            },
            {
                path: 'category/:categoryId',
                name: Pages.CategoryDetail,
                component: () => import("@/views/front/CategoryDetail.vue"),
            },
            {
                path: 'archives',
                name: Pages.Archives,
                component: () => import("@/views/front/Archives.vue")
            },
        ]
    },
    {
        path: '/admin',
        name: Pages.Admin,
        component: () => import("@/views/admin/Admin.vue"),
        children: [
            {
                path: 'dashboard',
                name: Pages.Dashboard,
                component: () => import("@/views/admin/Dashboard.vue"),
            },
        ]
    },

]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
