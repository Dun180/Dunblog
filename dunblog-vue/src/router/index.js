import { createRouter, createWebHistory } from 'vue-router'

import Index from "@/views/front/Index";
import Login from "@/views/end/Login";
import BlogDetail from "@/views/front/BlogDedail";
import BlogEdit from "@/views/end/BlogEdit";
import Backstage from "@/views/end/Backstage";
import Error from "@/views/Error";
import CategoryEdit from "@/views/end/CategoryEdit";

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/blog/:blogId',
    name: 'BlogDetail',
    component: BlogDetail
  },
  {
    path: '/blog/edit',
    name: 'BlogEdit',
    component: BlogEdit,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/backstage',
    name: 'Backstage',
    component: Backstage,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/category/edit',
    name: 'CategoryEdit',
    component: CategoryEdit,
    meta: {
      requireAuth: true
    }
  },
  {
    path: '/error',
    name:'Error',
    component: Error
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
