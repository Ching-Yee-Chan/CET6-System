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

const routes = [{
        path: '/login',
        name: 'Login',
        component: () =>
            import ('../modules/login/views/login')
    },
    {
        path: '/',
        component: Layout,
        redirect: '/main',
        name: 'Main',
        children: [{
            path: 'main',
            component: () =>
                import ('../modules/main/views/main')
        }]
    },
    {
        path: '/examInfo',
        component: Layout,
        redirect: '/examInfo/index',
        name: 'ExamInfo',
        children: [{
            path: 'index',
            component: () =>
                import ('../modules/examInfo/views/examInfo')
        }]
    },
    {
        path: '/personalCenter',
        component: Layout,
        redirect: '/personalCenter/index',
        name: 'PersonalCenter',
        children: [{
            path: 'index',
            component: () =>
                import ('../modules/personalCenter/views/personalCenter')
        }]
    },
    {
        path: '/online_exam',
        component: Layout,
        redirect: '/online_exam/index',
        name: 'OnlineExam',
        children: [{
            path: 'onlineExam',
            component: () =>
                import ('../modules/onlineExam/views/onlineExam')
        }]
    },
    {
        path: '/online_exam/examPage',
        name: 'ExamPage',
        component: () =>
            import ('../modules/onlineExam/views/examPage')
    }
]

const router = new VueRouter({
    routes
})

export default router