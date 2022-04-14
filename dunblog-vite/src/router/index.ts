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
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
