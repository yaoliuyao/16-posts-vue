<template>
    <div>
        <el-container>
            <el-header>
                <h1 @click="$router.push('/home')">
                    头部
                </h1>
            </el-header>
            <el-main>
                <el-row>
                    <el-col :span="18">
                        <div>文章详情</div>
                        <div>评论表单</div>
                        <div>评论列表</div>
                    </el-col>
                    <el-col :span="6">
                        <Banner></Banner>
                    </el-col>
                </el-row>
            </el-main>
            <el-footer>
                <Footer></Footer>
            </el-footer>
        </el-container>
    </div>
</template>

<script>
    import Footer from '../components/Footer';
    import Banner from '../components/Banner';
    import axios from 'axios';

    export default {
        name: "Post",
        components: {
            Footer, Banner
        },
        mounted() {
            alert(this.$route.params["id"]);
        }
    }
</script>

<style scoped>

</style>