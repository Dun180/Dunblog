import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Test from "@/views/Test";
import Index from "@/views/Index";
import Login from "@/views/Login";
import Blog from "@/views/Blog";
import BlogEdit from "@/views/BlogEdit";
import Backstage from "@/views/Backstage";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Index
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/blog',
    name: 'Blog',
    component: Blog
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
