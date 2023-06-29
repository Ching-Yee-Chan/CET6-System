import Vue from 'vue'
import VueRouter from 'vue-router'
// import Home from '../views/Home.vue'
import Layout from '../modules/layout/index'

Vue.use(VueRouter)
// 解决导航栏或者底部导航tabBar中的vue-router在3.0版本以上频繁点击菜单报错的问题。
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err);
};

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../modules/login/views/login')
  },
  {
    path: '/',
    component: Layout,
    redirect: '/main',
    name: 'Main',
    children: [
      {
        path: 'main',
        component: () => import('../modules/main/views/main')
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

export default router
