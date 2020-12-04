import VueRouter from "vue-router";
import Home from "./pages/Home";
import Post from "./pages/Post";

// 路由规则
// 返回路由对象
export default new VueRouter({
    routes: [
        {
            path: "/home", component: Home
        },
        {
            path: "/post/:id", component: Post
        }
    ]
})