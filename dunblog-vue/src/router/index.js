import { createRouter, createWebHistory } from 'vue-router'

import Index from "@/views/Index";
import Login from "@/views/Login";
import BlogDetail from "@/views/BlogDedail";
import BlogEdit from "@/views/BlogEdit";
import Backstage from "@/views/Backstage";

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
    path: '/blog',
    name: 'BlogDetail',
    component: BlogDetail
  },
  {
    path: '/blogedit',
    name: 'BlogEdit',
    component: BlogEdit
  },
  {
    path: '/backstage',
    name: 'Backstage',
    component: Backstage
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
