<template>
    <div>
        <el-container>
            <el-header>
                <div @click="$parent.currentComp = 'Home'">
                    头部
                </div>
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
        }
    }
</script>

<style scoped>

</style>