<template>
    <div class=".top">
        <el-header>
            <el-row align="middle" style="height: 60px" type="flex">
                <el-col :span="6">
                    <h1>My Personal Blog</h1>
                </el-col>
                <el-col :span="12">
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
        <el-container>
            <!-- 博客列表 -->
            <el-main class="posts">
                <div v-for="post in posts" :key="post.id">
                    <div class="post">
                        <h1 class="post-title"
                            @click="$router.push('/post/' + post.id)">{{post.title}}</h1>
                        <el-row :gutter="40">
                            <el-col :span="18" class="post-content">
                                {{post.content}}
                            </el-col>
                            <el-col :span="6" class="post-cover">
                                <el-image
                                        :src="'/myblog/' + post.cover"
                                        fit="contain"
                                        class="img-thumbnail"/>
                            </el-col>
                        </el-row>
                        <div class="post-footer">
                            <p class="post-author">{{post.author}}</p>
                            <p class="post-time">发布于 <i>{{post.created}}</i></p>
                            <div>
                                <span class="post-like">点赞 (<i>{{post.likes}}</i>) </span>
                                <span class="post-comment">评论 (<i>17</i>) </span>
                                <span class="post-share">分享 (<i>20</i>) </span>
                            </div>
                        </div>
                    </div>
                    <hr>
                </div>
            </el-main>
            <!-- 侧边栏 -->
            <el-aside width="350px" style="margin-left: 30px">
                <Banner></Banner>
            </el-aside>
        </el-container>
        <el-footer>
            <Footer></Footer>
        </el-footer>

        <el-dialog title="发表博客" :visible.sync="dialogFormVisible">
            <el-row :gutter="60">
                <el-col :span="16">
                    <el-form :model="form">
                        <el-form-item label="标题" :label-width="formLabelWidth">
                            <el-input v-model="form.title" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="内容" :label-width="formLabelWidth">
                            <el-input
                                    type="textarea"
                                    placeholder="请输入内容"
                                    v-model="form.content"
                                    maxlength="1000"
                                    utocomplete="off"
                                    show-word-limit>
                            </el-input>
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
                this.$axios({
                    url: "/posts"
                }).then(r => {
                    this.posts = r.data.data;
                });
            },
            savePost() {
                this.dialogFormVisible = false;

                let formData = new FormData();
                formData.append("author", this.form.author);
                formData.append("title", this.form.title);
                formData.append("content", this.form.content);
                formData.append("cover", this.form.cover);

                this.$axios({
                    url: '/post/add',
                    method: 'post',
                    data: formData
                }).then(r => {
                    this.loadPosts();

                    this.$refs.x.value = '';
                    this.form = {author: '张三'};

                    this.$message("保存成功！");
                });
            },
            filePick() {
                this.previewSrc = URL.createObjectURL(this.$refs.x.files[0]);
                this.form.cover = this.$refs.x.files[0];
            },
            test() {
                alert(this.$refs.x)
            }
        },
        created() {
            this.loadPosts();
        }
    }
</script>

<style scoped>
    .el-menu--horizontal {
        border-bottom: 0;
    }

    .el-footer {
        padding: 0;
    }

    .el-container {
        margin: 1em;
    }

    .el-main {
        margin: 0 1em;
    }

    .post {
        margin-bottom: 3em;
        padding: 1em;
    }

    .post-title {
        cursor: pointer;
    }

    .post-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-top: 1em;
    }

    .img-thumbnail {
        height: 150px;
        width: 150px;
    }

    .preview-img {
        width: 100px;
        height: auto;
    }
</style>