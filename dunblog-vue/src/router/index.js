import { createRouter, createWebHistory } from 'vue-router'

import Index from "@/views/Index";
import Login from "@/views/Login";
import BlogDetail from "@/views/BlogDedail";
import BlogEdit from "@/views/BlogEdit";
import Backstage from "@/views/Backstage";
import Error from "@/views/Error";

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
