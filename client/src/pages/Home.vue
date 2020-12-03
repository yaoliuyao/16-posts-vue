<template>
    <div>
        <el-container>
            <el-header>
                <el-row align="middle" style="height: 60px" type="flex">
                    <el-col :span="5">
                        <h1>My Personal Blog</h1>
                    </el-col>
                    <el-col :span="13">
                        <el-menu mode="horizontal">
                            <el-submenu index="1">
                                <template slot="title">分类</template>
                                <el-menu-item index="2-1">哲学</el-menu-item>
                                <el-menu-item index="2-2">游戏</el-menu-item>
                                <el-menu-item index="2-3">学习</el-menu-item>
                            </el-submenu>
                            <el-menu-item index="2">标签</el-menu-item>
                            <el-menu-item index="3" disabled>关于我们</el-menu-item>
                        </el-menu>
                    </el-col>
                    <el-col :span="6">
                        <el-button @click="dialogFormVisible = true">发表博客</el-button>
                        <el-button>用户管理</el-button>
                        <el-button @click="test">测试</el-button>
                    </el-col>
                </el-row>
            </el-header>
            <el-main>
                <el-row>
                    <!-- 主体内容 -->
                    <el-col :span="18" class="main">
                        <div class="posts">
                            <div class="post" v-for="post in posts" :key="post.id">
                                <el-row class="post-header">
                                    <el-col :span="20" class="post-content">
                                        <h3>{{post.title}}</h3>
                                        <p>
                                            {{post.content}}
                                        </p>
                                    </el-col>
                                    <el-col :span="4" class="post-cover">
                                        <img :src="'/api/' + post.cover" class="img-thumbnail">
                                    </el-col>
                                </el-row>
                                <el-row class="post-footer">
                                    <el-col class="post-author">{{post.author}}</el-col>
                                    <el-col class="post-time">发布于 <i>{{post.created}}</i></el-col>
                                    <el-col class="col-md-4">
                                        <span class="post-like">点赞 (<i>{{post.likes}}</i>) </span>
                                        <span class="post-comment">评论 (<i>17</i>) </span>
                                        <span class="post-share">分享 (<i>20</i>) </span>
                                    </el-col>
                                </el-row>
                            </div>
                        </div>
                    </el-col>
                    <!-- 侧边栏 -->
                    <el-col :span="6" class="aside">
                        <Banner></Banner>
                    </el-col>
                </el-row>
            </el-main>
            <el-footer>
                <Footer></Footer>
            </el-footer>
        </el-container>

        <el-dialog title="发表博客" :visible.sync="dialogFormVisible">
            <el-row :gutter="60">
                <el-col :span="16">
                    <el-form :model="form">
                        <el-form-item label="标题" :label-width="formLabelWidth">
                            <el-input v-model="form.title" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="内容" :label-width="formLabelWidth">
                            <el-input v-model="form.content" autocomplete="off"></el-input>
                        </el-form-item>
                        <div class="form-group" style="display: none">
                            <input type="file" ref="x" @change="filePick">
                            <input type="hidden" v-model="form.author">
                        </div>
                    </el-form>
                </el-col>
                <el-col :span="8">
                    <img @click="$refs.x.click()" class="preview-img" :src="previewSrc">
                    <p> 请点击选择图片 </p>
                </el-col>
            </el-row>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="savePost">发 表</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import Footer from '../components/Footer';
    import Banner from '../components/Banner';
    import axios from 'axios';

    export default {
        name: "Home",
        components: {
            Footer, Banner
        },
        data() {
            return {
                posts: [],
                dialogFormVisible: false,
                formLabelWidth: '80px',
                form: {
                    author: '张三',
                    title: '',
                    content: '',
                    cover: null
                },
                previewSrc: require("../assets/img/mm.jpg")
            }
        },
        methods: {
            loadPosts() {
                axios({
                    url: "/api/posts"
                }).then(r => {
                    console.log(r)
                    this.posts = r.data.data;
                }).catch(e => {
                    alert(e);
                });
            },
            savePost() {
                this.dialogFormVisible = false;

                var formData = new FormData();
                formData.append("author", this.form.author);
                formData.append("title", this.form.title);
                formData.append("content", this.form.content);
                formData.append("cover", this.form.cover);

                axios({
                    url: '/api/post/add',
                    method: 'post',
                    data: formData
                }).then(r => {
                    this.loadPosts();

                    this.$refs.x.value = '';
                    this.form = { author: '张三' };
                }).catch(e => {
                    alert(e);
                })
            },
            filePick() {
                this.previewSrc = URL.createObjectURL(this.$refs.x.files[0]);
                this.form.cover = this.$refs.x.files[0];
            },
            test () {
                alert(this.$refs.x)
            }
        },
        created() {
            this.loadPosts();
        }
    }
</script>

<style scoped>
    .main {
        min-height: 500px;
    }

    img {
        width: 100px;
        height: auto;
    }

    .post {
        padding: 1em 3em;
        border-bottom: 1px solid grey;
    }
</style>