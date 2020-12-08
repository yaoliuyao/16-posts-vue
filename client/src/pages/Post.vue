<template>
    <div>
        <el-page-header @back="$router.push('/')" :content="post.title"/>
        <el-container>
            <el-main>
                <div class="post">
                    <h1 class="post-title"
                        @click="$router.push('/post/' + post.id)">{{post.title}}</h1>
                    <el-row :gutter="40">
                        <el-col :span="18" class="post-content">
                            {{post.content}}
                        </el-col>
                        <el-col :span="6" class="post-cover">
                            <el-image
                                    :src="post.cover ? '/myblog/' + post.cover : ''"
                                    fit="contain"
                                    class="img-thumbnail"/>
                        </el-col>
                    </el-row>
                    <div class="post-footer">
                        <p class="post-author">{{post.author}}</p>
                        <p class="post-time">发布于 <i>{{post.created}}</i></p>
                        <div>
                            <span class="post-like" @click="likePost">
                                <i :class="likeIt"></i>
                                (<i>{{post.likes}}</i>) </span>
                            <span class="post-share">
                                <i class="el-icon-share"></i>
                                (<i>20</i>)
                            </span>
                        </div>
                    </div>
                </div>
                <div class="comment-form">
                    <h3>进行评论</h3>
                    <el-input
                            v-model="comment.author"
                            placeholder="请输入您的名字"
                            autocomplete="off"/>
                    <el-input
                            type="textarea"
                            placeholder="请输入您的评论"
                            v-model="comment.content"
                            maxlength="1000"
                            autocomplete="off"
                            show-word-limit/>
                    <div>
                        <el-button><i class="el-icon-refresh-right"/></el-button>
                        <el-button @click="saveComment"><i class="el-icon-s-promotion"/></el-button>
                    </div>
                </div>
                <div class="comment-list">
                    <h3>评论列表</h3>
                    <div v-if="this.comments.length === 0">暂无评论</div>
                    <div v-else class="comment" v-for="(comment, index) in comments" :key="comment.id">
                        <el-row>
                            <el-col :span="2">
                                <el-image
                                        :src="'/myblog/img/mm.jpg'"
                                        fit="contain"
                                        class="img-head"/>
                            </el-col>
                            <el-col :span="22" class="comment-head">
                                <div>第 {{index + 1}} 楼</div>
                                <div>{{comment.author}}</div>
                                <div>{{comment.created}}</div>
                            </el-col>
                        </el-row>
                        <p>
                            {{comment.content}}
                        </p>
                        <footer>
                            回复 点赞 <span @click="deleteComment(comment.id)">删除</span>
                        </footer>
                    </div>
                </div>
            </el-main>
            <el-aside>
                <Banner></Banner>
            </el-aside>
        </el-container>
        <el-footer>
            <Footer></Footer>
        </el-footer>
    </div>
</template>

<script>
    import Footer from '../components/Footer';
    import Banner from '../components/Banner';

    export default {
        name: "Post",
        components: {
            Footer, Banner
        },
        data() {
            return {
                post: {
                    id: null
                },
                comment: {
                    author: "",
                    content: ""
                },
                comments: [],
                likeIt: 'el-icon-star-off'
            }
        },
        methods: {
            loadPost(id) {
                this.$axios({
                    url: "/post?id=" + id
                }).then(r => {
                    this.post = r.data.data;
                });
            },
            loadComments(postid) {
                this.$axios({
                    url: "/comments?postid=" + postid
                }).then(r => {
                    this.comments = r.data.data;
                });
            },
            likePost() {
                if (this.likeIt === 'el-icon-star-off') {
                    this.$axios({
                        url: "/post/like?id=" + this.post.id
                    }).then(_ => {
                        this.likeIt = 'el-icon-star-on';
                        this.loadPost(this.post.id);
                    });
                } else {
                    this.$message("不能重复点赞");
                }

            },
            saveComment() {
                if (!this.comment.author || !this.comment.content) {
                    this.$alert("你这是搞啥？");
                    return;
                }

                let data = new URLSearchParams();
                data.append("postid", this.post.id);
                data.append("author", this.comment.author);
                data.append("content", this.comment.content);

                this.$axios({
                    method: "post",
                    url: "/comment/add",
                    data: data
                }).then(r => {
                    this.$message("评论发表成功！");
                    this.loadComments(this.post.id);
                    this.comment = {};
                });
            },
            deleteComment(id) {
                this.$confirm("确定要删除?")
                    .then(() => {
                        this.$axios({
                            url: '/comment/del?id=' + id
                        }).then(r => {
                            this.$message("删除成功!")
                            this.loadComments(this.post.id)
                        });
                    });
            }
        },
        created() {
            this.post.id = this.$route.params["id"];
            this.loadPost(this.post.id);
            this.loadComments(this.post.id);
        }
    }


</script>

<style scoped>
    i {
        font-style: normal;
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

    .comment {
        margin-bottom: 2em;
    }

    .post-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-right: 2em;
    }

    .post-like {
        cursor: pointer;
        margin-right: 8px;
    }

    .comment-head {
        display: flex;
    }

    .comment-head div {
        margin-left: 1em;
    }

    .el-input, .el-textarea {
        margin-bottom: 1em;
    }

    .comment-form, .comment-list {
        margin-top: 2em;
    }

    .img-thumbnail {
        height: 150px;
        width: 150px;
    }
</style>

<style>
    .el-page-header {
        background: black;
        padding: 1em;
    }
    .el-page-header > div {
        color: white;
    }
</style>